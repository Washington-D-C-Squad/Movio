package com.example.movio

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.data.dataSource.remote.SearchRemoteDataSource
import com.example.designsystem.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme (){

                val x = SearchRemoteDataSource()
                runBlocking {
                    GlobalScope.launch {
                        try {
                            x.searchPersonByName(
                                name = "hi",
                                language = "en-US"
                            ).collect {
                                withContext(Dispatchers.Main) {
                                    Log.e("MY_TAG" , it.toString())
                                }
                            }
                        }catch (e: Exception){
                            Log.e("MY_TAG" , e.toString())

                        }



                    }
                }
            }

        }
    }
}
