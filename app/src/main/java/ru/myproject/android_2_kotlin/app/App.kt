package ru.myproject.android_2_kotlin.app

import android.app.Application
//import android.os.Handler
//import android.os.HandlerThread
import androidx.room.Room
//import io.reactivex.rxjava3.core.Single
//import io.reactivex.rxjava3.schedulers.Schedulers
import ru.myproject.android_2_kotlin.room.HistoryDao
import ru.myproject.android_2_kotlin.room.HistoryDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDao(): HistoryDao {
            if (db == null) {
                synchronized(HistoryDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw
                        IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            HistoryDataBase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.historyDao()
        }
    }
}

//        fun getHistoryDao(): HistoryDao {
//            Single.zip(
//                //Выполняем запрос на сервер
//                if (db == null) {
//                    synchronized(HistoryDataBase::class.java) {
//                        if (db == null) {
//                            if (appInstance == null) throw
//                            IllegalStateException("Application is null while creating DataBase")
//                            db = Room.databaseBuilder(
//                                appInstance!!.applicationContext,
//                                HistoryDataBase::class.java,
//                                DB_NAME
//                            ).build()
//                        }
//                    }
//                }
//
//            )
//                .subscribeOn(Schedulers.io())   //выполним запрос в отдельном потоке
//                .observeOn(AndroidSchedulers.mainThread(db))  //Возвращаем полученные данные в основной поток
//                .subscribeBy()
//            return db!!.historyDao()
//        }
//    }
//}

//        fun getHistoryDao(): HistoryDao {
//            val handlerThreadDb = HandlerThread("HANDLER_THREAD_DB")
//            handlerThreadDb.start()
//            val handlerDb = Handler(handlerThreadDb.looper)
//            Thread {
//                if (db == null) {
//                    synchronized(HistoryDataBase::class.java) {
//                        if (db == null) {
//                            if (appInstance == null) throw
//                            IllegalStateException("Application is null while creating DataBase")
//                            db = Room.databaseBuilder(
//                                appInstance!!.applicationContext,
//                                HistoryDataBase::class.java,
//                                DB_NAME
//                            ).build()
//                            handlerDb.post {
//                                db!!.historyDao()
//                            }
//                        }
//                    }
//                }
//            }.start()
//            return db!!.historyDao()
//        }
//    }
//}