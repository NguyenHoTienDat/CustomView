package com.example.framgianguyenhotiendat.customeviewex1.indicator

class PageException : Exception() {

    override val message: String?
        get() = "The number of page must larger than 1"
}