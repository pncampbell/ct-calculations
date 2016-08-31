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

package uk.gov.hmrc.ct.accounts.frs10x.abridged

import org.mockito.Mockito._
import uk.gov.hmrc.ct.accounts.frs10x.{AccountsMoneyValidationFixture, MockAbridgedAccountsRetriever}
import uk.gov.hmrc.ct.box.CtValidation

class AC209Spec extends AccountsMoneyValidationFixture with MockAbridgedAccountsRetriever {

  override def setUpMocks() = {
    super.setUpMocks()

    import boxRetriever._

    when(ac42()).thenReturn(AC42(Some(100)))
  }

  testAccountsMoneyValidation("AC209", AC209.apply, testEmpty = false)

  "AC209" should {

    "throw error when is set when AC42 is empty" in {
      setUpMocks()
      when(boxRetriever.ac42()).thenReturn(AC42(None))
      AC209(Some(10)).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC209"), "error.AC209.cannot.exist"))
    }

    "validate successfully if nothing is wrong" in {
      setUpMocks()
      AC209(Some(10)).validate(boxRetriever) shouldBe Set.empty
    }

  }

}