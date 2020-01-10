package com.example.currencyconverter.rates.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.core.log.Log
import com.example.currencyconverter.rates.databinding.ItemRatesBinding
import kotlinx.android.synthetic.main.item_rates.view.*

/**
 * RecyclerView adapter for the list of rates. Uses asynchronous diffing for better UI performance,
 * and data binding to bind values to the views.
 */
internal class RatesAdapter(
    private val viewModel: RatesViewModel
) : RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Rate>() {

        override fun areItemsTheSame(oldItem: Rate, newItem: Rate) =
            oldItem.acronym == newItem.acronym

        override fun areContentsTheSame(oldItem: Rate, newItem: Rate) =
            oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRatesBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position], position)
    }

    /**
     * Updates this adapter with a new set of data.
     *
     * @param newData the new data to update.
     */
    internal fun update(newData: List<Rate>) {
        Log.d("update")
        differ.submitList(newData)
    }

    /**
     * ViewHolder pattern to hold the view data.
     */
    internal inner class ViewHolder(
        private val binding: ItemRatesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds a rate item to the view.
         */
        fun bind(item: Rate, position: Int) {
            binding.rate = item
            binding.viewModel = viewModel

            val textWatcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.isEmpty() == true) s.insert(0, "0.0")

                    viewModel.updateValues(s?.toString()?.toDouble() ?: 0.0)
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // Nothing
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Nothing
                }
            }

            // Only editing the base currency will change the values of the other currencies.
            if (position == 0) {
                binding.root.rateItem_value.addTextChangedListener(textWatcher)
            }
        }
    }
}
