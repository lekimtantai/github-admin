package dev.lekim.githubadmin.util.shared

import dev.lekim.githubadmin.data.shared.ErrorMessage

class RemoteException(val error: ErrorMessage) : Exception()

class LocalException(val errorMsg: String) : Exception()