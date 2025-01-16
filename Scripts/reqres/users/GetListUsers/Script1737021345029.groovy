import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper as JsonSlurper
//import com.kms.katalon.core.util.KeywordLogger
//
//KeywordLogger log = new KeywordLogger()
//
//log.logInfo("List Users - Berhasil mengambil list pengguna page 2 ")

// Menambahkan parameter untuk request
def params = [('page') : pageParam]

// Mengirim permintaan GET
response = WS.sendRequest(findTestObject('Object Repository/reqres/users/GET_ListUsers', params))

// Output respons untuk verifikasi
println(response.getResponseText())

// Parsing respons JSON menggunakan JsonSlurper
def jsonResponse = new JsonSlurper().parseText(response.getResponseText())

// Loop melalui daftar pengguna untuk mencetak nama depan, nama belakang, dan email
jsonResponse.data.each { user ->
    // Verifikasi bahwa email tidak null
    WS.verifyElementPropertyValue(response, "data[${jsonResponse.data.indexOf(user)}].email", user.email)
    
    // Verifikasi bahwa First Name tidak null
    WS.verifyElementPropertyValue(response, "data[${jsonResponse.data.indexOf(user)}].first_name", user.first_name)
    
    // Verifikasi bahwa Last Name tidak null
    WS.verifyElementPropertyValue(response, "data[${jsonResponse.data.indexOf(user)}].last_name", user.last_name)
}

// Verifikasi status kode respons
WS.verifyResponseStatusCode(response, 200)

// Verifikasi elemen properti dalam respons
WS.verifyElementPropertyValue(response, 'page', pageParam)

WS.verifyElementPropertyValue(response, 'per_page', jsonResponse.per_page)

WS.verifyElementPropertyValue(response, 'total', jsonResponse.total)

WS.verifyElementPropertyValue(response, 'total_pages',  jsonResponse.total_pages)

