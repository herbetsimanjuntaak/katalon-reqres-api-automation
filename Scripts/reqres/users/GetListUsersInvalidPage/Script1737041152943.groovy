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



// Menambahkan parameter untuk request
def params = [('page') : invalidPageParam]

// Mengirim permintaan GET
response = WS.sendRequest(findTestObject('Object Repository/reqres/users/GET_ListUsers', params))

// Output respons untuk verifikasi
println(response.getResponseText())

// Verifikasi status kode respons
WS.verifyResponseStatusCode(response, 200)

// Verifikasi bahwa data kosong pada respons
WS.verifyElementPropertyValue(response, 'data', [])

// Verifikasi bahwa total_pages masih sesuai dengan yang diharapkan
WS.verifyElementPropertyValue(response, 'total_pages', 2)

// Verifikasi bahwa total adalah jumlah total data
WS.verifyElementPropertyValue(response, 'total', 12)