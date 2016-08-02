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

package uk.gov.hmrc.ct.ct600.v2

import uk.gov.hmrc.ct.box.{Calculated, CtBoxIdentifier, CtOptionalInteger}
import uk.gov.hmrc.ct.computations.HmrcAccountingPeriod
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever
import uk.gov.hmrc.ct.ct600.v2.calculations.CorporationTaxCalculator


case class B53(value: Option[Int]) extends CtBoxIdentifier("Financial Year") with CtOptionalInteger

object B53 extends CorporationTaxCalculator with Calculated[B53, ComputationsBoxRetriever] {

  override def calculate(fieldValueRetriever: ComputationsBoxRetriever): B53 =
    B53(financialYear2(HmrcAccountingPeriod(fieldValueRetriever.cp1(), fieldValueRetriever.cp2())))

}
