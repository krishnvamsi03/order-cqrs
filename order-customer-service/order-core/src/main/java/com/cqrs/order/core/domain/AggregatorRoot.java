package com.cqrs.order.core.domain;

import com.cqrs.order.core.events.BaseEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class AggregatorRoot {

    private String id;
    private int version = -1;

    private List<BaseEvent> changes = new LinkedList<>();

    public String getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void markChangesAsCommitted() {
        changes.clear();
    }

    public List<BaseEvent> getChanges() {
        return changes;
    }

    private void applyChanges(BaseEvent event, boolean isNewEvent) {
        try {
            Method method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            if (isNewEvent) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChanges(event, true);
    }

    public void replayEvents(List<BaseEvent> events) {
        events.forEach(e -> applyChanges(e, false));
    }
}
