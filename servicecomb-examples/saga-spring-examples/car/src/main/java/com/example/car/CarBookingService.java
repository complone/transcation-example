package com.example.car;

import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
class CarBookingService {
    private Map<Integer, CarBooking> bookings = new ConcurrentHashMap<>();
    
    @Compensable(compensationMethod = "cancel")
    void order(CarBooking booking) {
        if (booking.getAmount() > 10) {
            throw new IllegalArgumentException("can not order the cars large than ten");
        }
        booking.confirm();
        bookings.put(booking.getId(), booking);
    }
    
    void cancel(CarBooking booking) {
        Integer id = booking.getId();
        if (bookings.containsKey(id)) {
            bookings.get(id).cancel();
        }
        // Just sleep a while to ensure the Compensated event is after ordering TxAbort event
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Just ignore the exception
        }
    }
    
    Collection<CarBooking> getAllBookings() {
        return bookings.values();
    }
    
    void clearAllBookings() {
        bookings.clear();
    }
}
