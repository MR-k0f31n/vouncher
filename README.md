# Микросервис для врачебной организации:

---
## Технологии:
    * java 11
    * spring framework 2.7.8
    * spring boot
    * spring JPA
    * PostgreSQL
---
## Предоставляемый сервис:
* Составить расписание приёма врача

      enpoint: hosts/ad/schedule/create
      Принимаемы параметры: 
      Long id - доктора
      LacalDate date - дата приёма
      LocalTime startRime - время начала приёма
      LocalTime shiftEndTime - время окончания приёма врача
      Integer timeReceipt - (опц.) время отведенное на приём по талону(слоту времени)
      установленно дефолтное значение 15 минут

* Получить все свободные талоны(слоты времени) на определеный день

      endpoint: hosts/registration/{doctorId}
      Принимаемы параметры: 
      переменная пути Long doctorId - id доктора в локальной БД
      LocalDate date - желаема дата приёма врача

* Получить все занятые талоны(слоты времени) определенного пациента

      endpoint: hosts/registration/{patientId}
      Принимаемы параметры:
      переменная пути Long patientId - id пациента в локальной БД

* Зарегистрировать свободный талон(слот времени)

      endpoint: hosts/registration/{patientId}&{timeSlotId}
      Принимаемы параметры:
      переменные пути Long patientId и Long timeSlotId - id пациента и талона(слота времени) в локальной БД