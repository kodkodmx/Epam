package com.epam.rd.autotask.io.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class OrderSerializer {

    public static void serializeOrder(Order order, OutputStream sink) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(sink)) {
            out.writeObject(order);
        }
    }

    public static Order deserializeOrder(InputStream sink) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(sink)) {
            return (Order) in.readObject();
        }
    }
}
