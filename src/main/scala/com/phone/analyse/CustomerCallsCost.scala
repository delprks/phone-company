package com.phone.analyse

case class CustomerCallsCost(id: String, cost: Double) {
  override def toString: String = s"Customer ID: $id, total cost of calls: £${Math.ceil(cost) / 100}"
}
