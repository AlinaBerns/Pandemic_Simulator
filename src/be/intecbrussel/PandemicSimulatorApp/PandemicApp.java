package be.intecbrussel.PandemicSimulatorApp;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class PandemicApp {
    public static void main(String[] args) {

        //Opdracht 1( /1 ):
        // De hoofdverpleegster wilt dat je alle patienten in een lijst stopt
        // die ervoor zorgt dat elkePatient er maar 1 keer in voorkomt,
        // liefst op volgorde dat ze binnen kwamen.
        // ---Zorg ervoor dat je een Collection gebruikt die alleen unieke elementen weergeeft,
        // en alles involgorde houdt. Print de lijst af.
        System.out.println("*** Opdracht 1( /1 ) *** \n");

        Collection<Patient> patients = new LinkedHashSet<>(Patient.getAllPatients());
        patients.forEach(System.out::println);

        //Terwijl dat je de lijst aan het opmaken ben, krijgen jullie een noodbericht van de regering:
        // er iseen nieuw onbekend virus uitgebroken!
        // Het lijkt erop dat de regering vraagt om voorrang te verlenen aan patiënten die een hogetemperatuur hebben,
        // en dan aan patiënten met een hogere leeftijd.

        System.out.println("\n*** Opdracht 2( /3 ) *** \n");
        System.out.println("\nPatiënten die een hogetemperatuur hebben: \n");

        Collection<Patient> temperature = new LinkedHashSet<>
                (Set.of(patients
                        .stream()
                        .sorted(Comparator.comparing(Patient::getTemperature).reversed())
                        .toArray(Patient[]::new)));

        temperature.forEach(System.out::println);

        System.out.println("\nPatiënten met een hogere leeftijd: \n");

        Collection<Patient> age = new LinkedHashSet<>
                (Set.of(patients
                        .stream()
                        .sorted(Comparator.comparing(Patient::getAge).reversed())
                        .toArray(Patient[]::new)));

        age.forEach(System.out::println);

        System.out.println("\n*** Bonus *** \n");
        //Je baas merkt op dat dit hospitaal werkt op een Amerikaans systeem:
        // als patiënten eenverzekering hebben,
        // zou zij het wel leukvinden als je deze ook zou willen
        // voor trekken narekening te hebben gehouden
        // met de directieven van de regering.
        // ---Sorteer een List van patiënten aan de hand
        // van TemperatureSorter en AgeSorter
        // die deklasse Comparator<Patient> implementeren.
        // Optioneel make je een InsuranceSorter.
        // Je mag hier ook lambdas/streamsvoor gebruiken!
        // Giet de resultaten achteraf in een Queue. Print de lijst af.

        Comparator<Patient> patientComparatorTemp = new TemperatureSorter();
        Comparator<Patient> patientComparatorAge = new AgeSorter();

        Queue<Patient> result = patients.stream()
                .filter(Patient::isEnsured)
                .sorted(patientComparatorTemp.reversed())
                .sorted(patientComparatorAge.reversed())
                .collect(Collectors.toCollection(LinkedBlockingQueue::new));

        result.forEach(System.out::println);


        System.out.println("\n*** Opdracht 3( /4 ) *** \n");

        //category1
        System.out.println("\n*** category1 *** \n");

        Collection<Patient> category1 = patients
                        .stream()
                        .filter(patient -> patient.getAge() <= 65 && patient.getTemperature() >= 38
                                || patient.getTemperature() >= 40 && patient.isUnknownVirus())
                        .collect(Collectors.toCollection(LinkedBlockingQueue::new));

        category1.forEach(System.out::println);

        //category2
        System.out.println("\n*** category2 *** \n");

        Collection<Patient> category2 = patients
                        .stream()
                        .filter(patient -> (patient.getTemperature() <= 38 || patient.getTemperature() >= 38) && patient.isUnknownVirus())
                        .collect(Collectors.toCollection(LinkedBlockingQueue::new));

        category2.forEach(System.out::println);

        //category3
        System.out.println("\n*** category3 *** \n");

        Collection<Patient> category3 = patients
                        .stream()
                        .filter(patient -> patient.getTemperature() <= 36 && patient.isUnknownVirus())
                        .collect(Collectors.toCollection(LinkedBlockingQueue::new));

        category3.forEach(System.out::println);


        //category4
        System.out.println("\n*** category4 *** \n");

        Collection<Patient> category4 = patients
                        .stream()
                        .filter(patient -> patient.getTemperature() >= 38 && !patient.isUnknownVirus())
                        .collect(Collectors.toCollection(LinkedBlockingQueue::new));

        category4.forEach(System.out::println);

        System.out.println("---------------------------------------------------------------");

        Map <Integer, Collection<Patient>> mapCollection = new HashMap<>();

        int key = 1;
        for (Patient p:category1) {
            mapCollection.putIfAbsent(key, category1);
        }

        int key2 = 2;
        for (Patient p:category2) {
            mapCollection.putIfAbsent(key2, category2);
        }

        int key3 = 3;
        for (Patient p:category3) {
            mapCollection.putIfAbsent(key3, category3);
        }

        int key4 = 4;
        for (Patient p:category4) {
            mapCollection.putIfAbsent(key4, category4);
        }

        for(Map.Entry<Integer, Collection<Patient>> category : mapCollection.entrySet()){
            System.out.printf("KEY: %s | VALUE: %s %n", category.getKey(), category.getValue());
        }













        }

    }


