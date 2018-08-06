package com.phone.format

import com.phone.analyse.CustomerCallsCost

object Formatter {
  def format(customerCost: CustomerCallsCost): String = s"Customer ID: ${customerCost.id}, total cost of calls: £${Math.ceil(customerCost.cost) / 100}"
}
