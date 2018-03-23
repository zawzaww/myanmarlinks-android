package com.zawzaw.savethelibrary.repository

/**
 * Created by zawzaw on 22/03/18.
 */

class NetworkState(val status: Status, val msg: String) {
    companion object {

        val LOADED: NetworkState
        val LOADING: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.RUNNING, "Running")
        }
    }

}
