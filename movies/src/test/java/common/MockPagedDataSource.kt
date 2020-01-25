package common

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class MockPagedDataSource<Key, Value>(
    private val data: List<Value>
) : DataSource.Factory<Key, Value>() {

    override fun create(): DataSource<Key, Value> {

        return object : PageKeyedDataSource<Key, Value>() {

            override fun loadInitial(
                params: LoadInitialParams<Key>,
                callback: LoadInitialCallback<Key, Value>
            ) {
                callback.onResult(data, null, null)
            }

            override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
                callback.onResult(data, null)
            }

            override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
                callback.onResult(data, null)
            }
        }
    }
}
