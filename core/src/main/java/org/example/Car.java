package org.example;

import lombok.Builder;

@Builder()
public record Car(long id, String brand, double price) {

}
