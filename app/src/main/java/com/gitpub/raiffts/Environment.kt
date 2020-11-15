package com.gitpub.raiffts


interface Environment {
    val apiUrl: String
    val secretKey: String
    val khabenskyId: String
}

class SandboxEnvironment : Environment {
    override val apiUrl = "https://test.ecom.raiffeisen.ru/api/sbp/v1"
    override val secretKey =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNQTYyMzA4NCIsImp0aSI6ImE0YmZkMzU5LTdjMmItNGI0Yy05N2Y1LTIwNDJmMTI4OTIyNCJ9.gM1Xfua7OsdecOcIj4WSdVHOjH4aBXSXV3ozcbswlm8"
    override val khabenskyId = "MA0000000552"
}

class ProductionEnvironment : Environment {
    override val apiUrl = "https://e-commerce.raiffeisen.ru/api/sbp/v1"
    override val secretKey =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNQTYyMzA4NCIsImp0aSI6ImE0YmZkMzU5LTdjMmItNGI0Yy05N2Y1LTIwNDJmMTI4OTIyNCJ9.gM1Xfua7OsdecOcIj4WSdVHOjH4aBXSXV3ozcbswlm8"
    override val khabenskyId = "MA0000000279"
}
