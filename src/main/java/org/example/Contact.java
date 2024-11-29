package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
/**
 * Контакт телефонной книги
 */
public class Contact {
    private String name;
    private String phoneNumber;

    @Override
    public String toString() {
        return name + " " + phoneNumber;
    }
}
