package org.example.application.views;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.example.application.entities.WeatherRequest;
import org.example.application.services.WeatherResponse;
import org.example.application.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("Погода")
@Route("/weather")
public class MainPageView extends HorizontalLayout {
    private final WeatherService weatherService;

    private final Component mainLayout;

    private final VerticalLayout userLayout;
    private final Component fieldsComponent;
    private final TextField longitudeField;
    private final TextField latitudeField;
    private final Button showWeatherButton;

    private final Component answerLayout;
    private final Span weatherSpan;
    private final Image weatherImage;
    private final Span temperatureSpan;
    private final VerticalLayout historyLayout;
    private final Span historySpan;


    @Autowired
    public MainPageView(WeatherService service) {
        this.weatherService = service;
        this.latitudeField = new TextField("Широта");
        this.longitudeField = new TextField("Долгота");
        this.showWeatherButton = new Button("Показать погоду");
        this.weatherSpan = new Span();
        this.temperatureSpan = new Span();
        this.weatherImage = new Image();
        this.historySpan = new Span("История");
        this.historyLayout = new VerticalLayout(historySpan);


        showWeatherButton.addClickListener(event -> showWeather());
        showWeatherButton.addClickShortcut(Key.ENTER);

        setMargin(true);
        setSpacing(true);

        this.fieldsComponent = new HorizontalLayout(
                Alignment.CENTER,
                latitudeField,
                longitudeField
        );

        this.answerLayout = new HorizontalLayout(
                Alignment.CENTER,
                weatherImage,
                weatherSpan
        );
        this.userLayout = new VerticalLayout(
                fieldsComponent,
                showWeatherButton,
                answerLayout,
                temperatureSpan
        );
        this.mainLayout = new HorizontalLayout(
                Alignment.AUTO,
                userLayout,
                historyLayout
        );

        add(mainLayout);
        updateHistory();
    }

    private void showWeather() {
        try {
            double latitudeValue = Double.parseDouble(latitudeField.getValue());
            double longitudeValue = Double.parseDouble(longitudeField.getValue());
            WeatherResponse weatherResponse = weatherService.getWeather(latitudeValue, longitudeValue);
            String weatherInfo = weatherResponse.weather.get(0).description;
            String weatherDetails = String.format("Температура: %.2f°C, Ощущается как: %.2f°C",
                    weatherResponse.main.temp,
                    weatherResponse.main.feels_like
            );
            String iconUrl = "http://openweathermap.org/img/wn/" + weatherResponse.weather.get(0).icon + "@2x.png";
            weatherSpan.setText(weatherInfo);
            temperatureSpan.setText(weatherDetails);
            weatherImage.setSrc(iconUrl);
            weatherImage.setAlt("Weather Image");

            weatherService.saveRequest(String.valueOf(latitudeValue), String.valueOf(longitudeValue), weatherResponse);
            updateHistory();
        } catch (NumberFormatException e) {
            Notification.show("Введите корректные координаты", 3000, Notification.Position.MIDDLE);
        } catch (JsonProcessingException e) {
            Notification.show("JSON Exception!");
            throw new RuntimeException(e.getMessage());
        }
    }

    private void updateHistory() {
        historyLayout.removeAll();
        historyLayout.add(historySpan);
        List<WeatherRequest> requests = weatherService.getAllRequests();
        for (WeatherRequest request : requests) {
            String stringRequest = request.toString();
            historyLayout.add(new Span(stringRequest));
        }
    }
}



