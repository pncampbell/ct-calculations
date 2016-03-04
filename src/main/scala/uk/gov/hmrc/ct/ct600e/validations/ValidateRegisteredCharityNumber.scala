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

package uk.gov.hmrc.ct.ct600e.validations

import uk.gov.hmrc.ct.box.CtValidation

trait ValidateRegisteredCharityNumber {

  def validate(value: Option[String], boxId: String): Set[CtValidation] = value match {
    case Some(v) if v.length < 6 || v.length > 8 || v.exists(!_.isDigit) => Set(CtValidation(Some(boxId), s"error.$boxId.invalidRegNumber"))
    case _ => Set()
  }

}