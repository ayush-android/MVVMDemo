package com.livedatademo.with_room_database.data

import android.content.Context
import com.livedatademo.R
import com.livedatademo.utils.AppExecutors
import com.livedatademo.with_room_database.data.model.LoggedInUser
import java.io.IOException
import java.util.concurrent.Callable

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(context: Context) {

    var appContext: Context = context.applicationContext

    fun loginUser(username: String, password: String): Result<LoggedInUser> {
        return try {
            AppExecutors().diskIO().submit(Callable<Result<LoggedInUser>> {
                val db = UserDatabase.getInstance(appContext)
                val user = db.userDao.getPassword(username)

                if (user == null) {
                    Result.ErrorMsg(appContext.getString(R.string.user_not_exist))
                } else {
                    if (user.pass == password) {
                        Result.Success(user)
                    } else {
                        Result.ErrorMsg(appContext.getString(R.string.wrong_password))
                    }
                }
            }).get()
        } catch (e: Exception) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            var user: LoggedInUser? = null

            user = LoggedInUser(java.util.UUID.randomUUID().toString(), username, password)
            return Result.Success(user!!)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}