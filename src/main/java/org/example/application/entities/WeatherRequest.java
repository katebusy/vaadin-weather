package org.example.application.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(schema = "weather", name = "weather_requests")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class WeatherRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Column(name = "request_time")
    private Timestamp date;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "answer")
    private String answer;

    @Override
    public String toString() {
        String time = date.toLocalDateTime().toLocalTime().toString();
        String timeFormatted = time.substring(0, time.lastIndexOf("."));
        return timeFormatted + " | широта: " +
                longitude + ", долгота: " +
                latitude + " | ответ: " +
                answer;
    }
}
