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

// Menentukan Id User
String idUser = '2'

// Menambahkan parameter untuk request
def paramsGetID = [('id') : idUser]

// Mengirim request GET untuk mendapatkan data pengguna berdasarkan ID
response = WS.sendRequest(findTestObject('reqres/users/GET_SingleUser', [('id') : '2']))

// Mengonversi response JSON menjadi objek
def jsonResponseSingleUser = new JsonSlurper().parseText(response.getResponseText())

// Mendapatkan email dari response single user
def email = jsonResponseSingleUser.data.email

println('Email Single user = ' + email)

// Menentukan password untuk pendaftaran user
String password = 'password'

// Membuat payload untuk request POST untuk registrasi user
def payload = [('email') : email, ('password') : password]

// Mengirim request POST untuk Register user
responseRegisterUser = WS.sendRequest(findTestObject('reqres/register/POST_Register',payload))

println(responseRegisterUser.getResponseText())

// Mengonversi response JSON menjadi objek
def jsonResponseCreate = new JsonSlurper().parseText(responseRegisterUser.getResponseText())

// Mendapatkan ID dan token dari response register user
def idCreate = jsonResponseCreate.id

def tokenCreate = jsonResponseCreate.token

// Verifikasi status kode respons
WS.verifyResponseStatusCode(responseRegisterUser, 200)

WS.verifyElementPropertyValue(responseRegisterUser, 'id', idCreate)

WS.verifyElementPropertyValue(responseRegisterUser, 'token', tokenCreate)

