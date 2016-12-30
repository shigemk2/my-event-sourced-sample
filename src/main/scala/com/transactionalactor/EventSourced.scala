package com.transactionalactor

import akka.actor._

object EventSourcedDriver extends CompletableApp(4) {
}

trait DomainEvent

case class StartOrder(orderId: String, customerInfo: String)
case class OrderStarted(orderId: String, customerInfo: String) extends DomainEvent

case class AddOrderLineItem(productId: String, units: Int, price: Double)
case class OrderLineItemAdded(productId: String, units: Int, price: Double) extends DomainEvent

case class PlaceOrder()
case class OrderPlaced() extends DomainEvent
