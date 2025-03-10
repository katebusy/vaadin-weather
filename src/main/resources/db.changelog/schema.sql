create extension if not exists "uuid-ossp";

create schema weather;

create table weather.weather_requests (
    id uuid default uuid_generate_v1() primary key,
    request_time timestamp not null,
    longitude text not null,
    latitude text not null,
    answer text not null
);
