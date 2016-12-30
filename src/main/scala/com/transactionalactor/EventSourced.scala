package com.transactionalactor

import akka.actor.Actor.Receive
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

class Order extends EventsourcedProcessor {
  var state: OrderState = _ // new OrderState()

  def updateState(event: DomainEvent): Unit =
    state = state.update(event)

  val receiveRecover: Receive = {
    case event: DomainEvent =>
      updateState(event)
    case SnapshotOffer(_, snapshot: OrderState) =>
      state = snapshot
  }

  val receiveCommand: Receive = {
    case startOrder: StartOrder =>
      persist(OrderStarted(startOrder.orderId, startOrder.customerInfo)) (updateState)
    case addOrderLineItem @ AddOrderLineItem =>
    //persist(OrderStarted(addOrderLineItem., startOrder.customerInfo)) (updateState)
    case placeOrder @ PlaceOrder =>
  }
}

