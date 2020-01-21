package com.example.currencyconverter.rates.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfinatti.matheusmovies.core.log.Log
import com.example.currencyconverter.core.rates.domain.Currency
import com.example.currencyconverter.core.rates.domain.RatesRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * ViewModel for Rates screen.
 *
 * @param requestDelay delay between requests in ms.
 * @param ratesRepository a repository to obtain the currency rates.
 */
internal class RatesViewModel @Inject constructor(
    private val requestDelay: Long,
    private val ratesRepository: RatesRepository
) : ViewModel() {

    val ratesLiveData: LiveData<RatesUiModel>
    get() = _ratesLiveData

    private var currentBaseValue: Double = 0.0

    private val _ratesLiveData = MutableLiveData<RatesUiModel>()

    private val compositeDisposable = CompositeDisposable()

    /**
     * Loads rates to an observable that emits the currency rates every 1 second. The values are
     * based on the base currency and the base value arguments. E.g. Base currency is EUR and value
     * is 100, then the observable will emit all currencies such as USD with value 110 if the rate
     * 1.1.
     *
     * @param base the base currency object.
     * @param baseValue the base value to convert the other currencies.
     *
     */
    fun loadRates(base: Currency, baseValue: Double) {
        Log.d("loadRates, base: $base, value: $baseValue")
        currentBaseValue = baseValue

        val baseRate = Rate(base, baseValue)
        compositeDisposable.clear()

        val disposable = ratesRepository.getRates(base)
            .subscribeOn(Schedulers.io())
            .repeatWhen { completed -> completed.delay(requestDelay, TimeUnit.MILLISECONDS) }
            .subscribeBy(
                onError = {
                    _ratesLiveData.postValue(RatesUiModel.Error)
                },
                onNext = { result ->
                    // Transform the result from the backend into something the UI can consume.
                    val rates = result.map { currency -> Rate(currency, currentBaseValue) }
                    _ratesLiveData.postValue(RatesUiModel.Rates(listOf(baseRate) + rates))
                }
            )

        compositeDisposable.add(disposable)
    }

    /**
     * Updates only the values, which does not trigger a call to the endpoint.
     *
     * @param baseValue the new base value.
     */
    fun updateValues(baseValue: Double) {
        val currentRates = (_ratesLiveData.value as? RatesUiModel.Rates)?.rates ?: listOf()
        currentBaseValue = baseValue

        _ratesLiveData.postValue(
            RatesUiModel.Rates(
                listOf(currentRates[0]) +
                        currentRates.drop(1).map { it.copy(value = baseValue * it.rate) }
            )
        )
    }

    @VisibleForTesting
    internal fun setLiveDataValue(rates: List<Rate>) {
        _ratesLiveData.value = RatesUiModel.Rates(rates)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
