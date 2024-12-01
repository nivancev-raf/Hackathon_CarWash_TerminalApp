package com.cyb.payten_windowsxp_terminalapp.payment

data class RequestJson(
    val header: Header,
    val request: Request
)

data class Header(
    val length: String = "259",
    val hash: String = "XXX",
    val version: String = "01"
)

data class Request(
    val financial: Financial
)

data class Financial(
    val transaction: String = "sale",
    val id: Id,
    val amounts: Amounts,
    val options: Options
)

data class Id(
    val XXXecr: String = "000008",
    val XXXacquirer: String = "AQ2",
    val XXXcardName: String = "kukuruz|B1|kokice"
)

data class Amounts(
    val base: String = "100.00",
    val currencyCode: String = "RSD"
)

data class Options(
    val language: String = "sr",
    val print: String = "true"
)
