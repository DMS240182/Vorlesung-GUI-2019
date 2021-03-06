package de.fhrosenheim.gui.u06.eventbus;

/**
 *  Sinmple event bus interface
 * 
 * @author dominik.haas
 */
public interface EventBus {

    /**
     * Publish a event to all registered listeners. 
     * The event is dispatched by using event.getClass().
     * @param event the event.
     */
    void publish(Event event);

    /**
     * Subscribe for a given type of event. The type is not polymorphic. 
     * You have to make a separate subscription for every concrete type.
     * @param type the event type to subscribe.
     * @param listener your listener, which will be called if a event happens.
     */
    <T extends Event> void subscribe(Class<T> type, IEventBusListener<T> listener);
}
