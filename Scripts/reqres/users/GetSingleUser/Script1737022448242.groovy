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
def params = [('id') : idUser]

// Mengirim permintaan GET
response = WS.sendRequest(findTestObject('Object Repository/reqres/users/GET_SingleUser', params))

println(response.getResponseText())

// Parsing respons JSON menggunakan JsonSlurper [import groovy.json.JsonSlurper]
def jsonResponse = new JsonSlurper().parseText(response.getResponseText())

// Mengakses nilai-nilai dari JSON response
def id = jsonResponse.data.id

def email = jsonResponse.data.email

def firstName = jsonResponse.data.first_name

def lastName = jsonResponse.data.last_name

def avatar = jsonResponse.data.avatar

println('ID: ' + id)

println('First Name: ' + firstName)

println('Last Name: ' + lastName)

println('Email: ' + email)

println('Avatar: ' + avatar)

// Verifikasi status kode respons
WS.verifyResponseStatusCode(response, 200)

// Verifikasi elemen properti dalam respons
WS.verifyElementPropertyValue(response, 'data.id', idUser)

WS.verifyElementPropertyValue(response, 'data.email', email)

WS.verifyElementPropertyValue(response, 'data.first_name', firstName)

WS.verifyElementPropertyValue(response, 'data.last_name', lastName)

WS.verifyElementPropertyValue(response, 'data.avatar', avatar)

