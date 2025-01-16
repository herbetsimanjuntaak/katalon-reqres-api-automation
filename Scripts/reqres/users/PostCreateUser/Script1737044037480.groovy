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

String name = 'morpheus'

String job = 'leader'

def payload = [('name') : name, ('job') : job]

// Mengirimkan request POST untuk membuat user
responseCreateuser = WS.sendRequest(findTestObject('reqres/users/POST_CreateUser', payload))

println(responseCreateuser.getResponseText())

def jsonResponseCreate = new JsonSlurper().parseText(responseCreateuser.getResponseText())

// Ambil nilai tertentu dari respons
def nameCreate = jsonResponseCreate.name
def jobCreate = jsonResponseCreate.job
def idCreate = jsonResponseCreate.id
def createdAt = jsonResponseCreate.createdAt

WS.verifyResponseStatusCode(responseCreateuser, 201)

WS.verifyEqual(nameCreate, name)
WS.verifyEqual(jobCreate, job)
WS.verifyElementPropertyValue(responseCreateuser,'id',idCreate)
WS.verifyElementPropertyValue(responseCreateuser,'createdAt',createdAt)
assert idCreate != null : "id tidak boleh null"
assert createdAt != null : "createdAt tidak boleh null"

