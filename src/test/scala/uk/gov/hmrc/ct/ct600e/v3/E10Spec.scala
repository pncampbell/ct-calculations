/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ct.ct600e.v3

import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.ct600e.v3.retriever.CT600EBoxRetriever

class E10Spec extends WordSpec with Matchers with MockitoSugar {

  val boxRetriever = mock[CT600EBoxRetriever]

  "E10" should {

    "return no errors if value is between 6-8 characters and all digits" in {
      E10(Some("123456")).validate(boxRetriever).isEmpty shouldBe true
    }

    "return no errors if value is None" in {
      E10(None).validate(boxRetriever).isEmpty shouldBe true
    }

    "return an error if value is less then 6 characters" in {
      E10(Some("12345")).validate(boxRetriever) shouldBe Set(CtValidation(Some("E10"), "error.E10.invalidRegNumber"))
    }

    "return an error if value is more then 8 characters" in {
      E10(Some("123456789")).validate(boxRetriever) shouldBe Set(CtValidation(Some("E10"), "error.E10.invalidRegNumber"))
    }

    "return an error if value has correct number of characters but contains any non-digit" in {
      E10(Some("1234a678")).validate(boxRetriever) shouldBe Set(CtValidation(Some("E10"), "error.E10.invalidRegNumber"))
    }
  }

}