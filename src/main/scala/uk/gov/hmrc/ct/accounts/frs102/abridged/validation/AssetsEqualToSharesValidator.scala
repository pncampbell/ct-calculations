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

package uk.gov.hmrc.ct.accounts.frs102.abridged.validation

import uk.gov.hmrc.ct.accounts.frs102.retriever.Frs102AccountsBoxRetriever
import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever
import uk.gov.hmrc.ct.box.{CtOptionalInteger, CtValidation, ValidatableBox}

trait AssetsEqualToSharesValidator extends ValidatableBox[Frs102AccountsBoxRetriever with FilingAttributesBoxValueRetriever] {
  self: CtOptionalInteger =>

  def validateAssetsEqualToShares(boxId: String, otherBox: CtOptionalInteger, isLimitedByGuarantee: Boolean): Set[CtValidation] = {
    collectErrors(
      failIf(value != otherBox.value && !isLimitedByGuarantee) {
        Set(CtValidation(None, s"error.$boxId.assetsNotEqualToShares"))
      },
      failIf(value != otherBox.value && isLimitedByGuarantee) {
        Set(CtValidation(None, s"error.$boxId.assetsNotEqualToMembersFunds"))
      }
    )
  }

}
