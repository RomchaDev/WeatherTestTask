package com.example.layer_data.mapping

interface Mapper<F, T> {
    fun map(from: F): T
}