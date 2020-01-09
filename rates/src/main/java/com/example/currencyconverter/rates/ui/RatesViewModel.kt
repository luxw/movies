package com.example.currencyconverter.rates.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.core.log.Log
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

    private val compositeDisposable = CompositeDisposable()

    /**
     * Gets an observable that emits the currency rates every 1 second. The values are based on
     * the base currency and the base value arguments. E.g. Base currency is EUR and value is 100,
     * then the observable will emit all currencies such as USD with value 110 if the rate 1.1.
     *
     * @param base the base currency object.
     * @param baseValue the base value to convert the other currencies.
     *
     * @return a live data observable which emits [RatesUiModel]s.
     */
    internal fun getRatesLiveData(base: Currency, baseValue: Double): LiveData<RatesUiModel> {
        Log.d("getRatesObservable, base: $base, value: $baseValue")

        val liveData = MutableLiveData<RatesUiModel>()
        compositeDisposable.clear()

        val disposable = ratesRepository.getRates(base)
            .subscribeOn(Schedulers.io())
            .repeatWhen { completed -> completed.delay(requestDelay, TimeUnit.MILLISECONDS) }
            .subscribeBy(
                onError = {
                    liveData.postValue(RatesUiModel.Error)
                },
                onNext = { result ->
                    // Transform the result from the backend into something the UI can consume.
                    val rates = result.map { currency -> Rate(currency, baseValue) }
                    liveData.postValue(RatesUiModel.Rates(rates))
                }
            )

        compositeDisposable.add(disposable)

        return liveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
