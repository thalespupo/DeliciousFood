package com.thalespupo.deliciousfood

import com.thalespupo.deliciousfood.api.ApiServiceBuilder
import org.junit.Assert
import org.junit.Test

class ApiServiceUnitTest {

    @Test
    fun testApi() {
        val service = ApiServiceBuilder().build()
        val call = service.getSandwiches()

        val response = call.execute()

        Assert.assertTrue(response.body()?.isEmpty()!!.not())
    }
}