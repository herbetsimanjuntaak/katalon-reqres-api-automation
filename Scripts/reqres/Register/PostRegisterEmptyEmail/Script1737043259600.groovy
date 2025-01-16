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

// Menentukan email yang valid
String email = ''
String password = 'password'

// Membuat payload untuk request POST 
def payload = [('email') : email, ('password') : password]

// Mengirim request POST untuk Register user
responseRegisterUser = WS.sendRequest(findTestObject('reqres/register/POST_Register', payload))

// Print response
println(responseRegisterUser.getResponseText())

// Mengonversi response JSON menjadi objek
def jsonResponseCreate = new JsonSlurper().parseText(responseRegisterUser.getResponseText())
def messageError = jsonResponseCreate.error
// Verifikasi status kode respons
WS.verifyResponseStatusCode(responseRegisterUser, 400)

// Verifikasi pesan error
WS.verifyElementPropertyValue(responseRegisterUser, 'error', messageError)