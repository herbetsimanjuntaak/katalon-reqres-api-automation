package reqres
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import groovy.json.JsonSlurper as JsonSlurper


class reqresKeywords {
	public static JsonSlurper jsonSlurper = new JsonSlurper()

	@Keyword
	def int createNewUser(String name, String job, int expectedStatus) {
		// Kirim POST request untuk create new user
		def response = WS.sendRequestAndVerify(findTestObject("Object Repository/POST Create New User",
				["name": name, "job": job]))

		// Mengurai response JSON
		def jsonResponse = jsonSlurper.parseText(response.getResponseText())

		// Verifikasi status kode sesuai ekspektasi
		WS.verifyResponseStatusCode(response, expectedStatus)

		// Mengembalikan ID yang diterima dari response
		return jsonResponse.id
	}


	@Keyword
	def int createNewRegister(String email, String password, int expectedStatus) {
		// Kirim POST request untuk register
		def response = WS.sendRequestAndVerify(findTestObject("reqres/users/POST_CreateUser",
				["email": email, "password": password]))

		// Mengurai response JSON
		def jsonResponse = jsonSlurper.parseText(response.getResponseText())

		// Verifikasi status kode sesuai ekspektasi
		WS.verifyResponseStatusCode(response, expectedStatus)

		// Memastikan adanya token di response
		assertThat(jsonResponse).containsKey("token")

		// Mengembalikan token yang diterima dari response
		return jsonResponse.token
	}
}