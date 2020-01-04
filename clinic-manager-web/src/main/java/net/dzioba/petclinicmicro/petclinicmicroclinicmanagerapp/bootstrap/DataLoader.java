package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.*;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;
    private final RoomDailyReservationService roomDailyReservationService;
    private final RoomReservationService roomReservationService;
    private final RoomService roomService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (noDataInModel()){
            saveData();
        }
        else {
            log.debug("No need to load data");
        }
    }

    private boolean noDataInModel() {
        return petTypeService.findAll().size() == 0;
    }

    private void saveData() {

        log.debug("Loading data process started");
        PetType dogPetType = PetType.builder().name("dog").build();
        dogPetType = petTypeService.save(dogPetType);

        PetType catPetType =PetType.builder().name("cat").build();
        catPetType = petTypeService.save(catPetType);

        log.debug("Loaded data: PetTypes");

        Pet michaelsDog = Pet.builder().birthDate(LocalDate.of(2017, 7, 3))
                .name("Suzu").petType(dogPetType).build();
        michaelsDog.setPetType(dogPetType);

        Owner owner1 = Owner.builder().firstName("Michael").lastName("Weston")
                .address("Street 132, City").telephone("123432123").build();
        michaelsDog = owner1.addPet(michaelsDog);
        owner1 = ownerService.save(owner1);

        Pet fionasCat = Pet.builder().birthDate(LocalDate.of(2018, 3, 12))
                .name("Chiki").petType(catPetType).build();

        Owner owner2 = Owner.builder().firstName("Fiona").lastName("Glenanne")
                .address("Street 74, City").telephone("53223423").build();
        fionasCat = owner2.addPet(fionasCat);
        owner2 = ownerService.save(owner2);

        log.debug("Loaded data: Owners and Pets");

        Speciality radiologySpeciality = Speciality.builder().description("radiology").build();
        radiologySpeciality = specialityService.save(radiologySpeciality);

        Speciality surgerySpeciality = Speciality.builder().description("surgery").build();
        surgerySpeciality = specialityService.save(surgerySpeciality);

        Speciality dentistSpeciality = Speciality.builder().description("dentist").build();
        // intentional lack of save for dentistSpeciality here

        Vet vet1 = Vet.builder().firstName("Sam").lastName("Axe").build();
        vet1.addSpeciality(surgerySpeciality);
        vet1 = vetService.save(vet1);

        Vet vet2 = Vet.builder().firstName("Jessie").lastName("Porter").build();
        vet2.addSpeciality(radiologySpeciality);
        vet2.addSpeciality(dentistSpeciality);
        vet2 = vetService.save(vet2);

        log.debug("Loaded data: Vets");

        Room room1 = Room.builder().name("PAW Patrol").description("room description").mainVet(vet1).build();
        roomService.save(room1);

        Room room2 = Room.builder().name("SMURFS Village").description("room description").mainVet(vet2).build();
        roomService.save(room2);

        RoomDailyReservation roomDailyReservation1 = RoomDailyReservation.builder()
                .room(room1).date(LocalDate.of(2020, 10, 30)).vet(vet1).build();

        RoomDailyReservation roomDailyReservation2 = RoomDailyReservation.builder()
                .room(room2).date(LocalDate.of(2020, 3, 30)).vet(vet2).build();

        roomDailyReservation1 = roomDailyReservationService.save(roomDailyReservation1);
        roomDailyReservation2 = roomDailyReservationService.save(roomDailyReservation2);


        RoomReservation roomReservation1 = RoomReservation.builder()
                .room(room1).reservationStart(RoomReservationStart._0900).roomDailyReservation(roomDailyReservation1).build();
        RoomReservation roomReservation2 = RoomReservation.builder()
                .room(room1).reservationStart(RoomReservationStart._1100).roomDailyReservation(roomDailyReservation1).build();
        RoomReservation roomReservation3 = RoomReservation.builder()
                .room(room2).reservationStart(RoomReservationStart._1030).roomDailyReservation(roomDailyReservation2).build();

        roomReservation1 = roomReservationService.save(roomReservation1);
        roomReservation2 = roomReservationService.save(roomReservation2);
        roomReservation3 = roomReservationService.save(roomReservation3);

        Visit visit1 = Visit.builder()
                .dateTime(LocalDateTime.of(2020, 10, 30, 9, 0))
                .description("Occasional visit").pet(fionasCat).owner(owner2).vet(vet1)
                .roomReservation(roomReservation1).build();
        visit1 = visitService.save(visit1);
        roomReservation1.setVisit(visit1);

        Visit visit2 = Visit.builder()
                .dateTime(LocalDateTime.of(2020, 10, 30, 11, 0))
                .description("Not really know why").pet(fionasCat).owner(owner2).vet(vet1)
                .roomReservation(roomReservation2).build();
        visit2 = visitService.save(visit2);
        roomReservation2.setVisit(visit2);

        Visit visit3 = Visit.builder()
                .dateTime(LocalDateTime.of(2020, 3, 30, 10, 30))
                .description("Just another occasional visit").pet(michaelsDog).owner(owner1).vet(vet2)
                .roomReservation(roomReservation3).build();
        visit3 = visitService.save(visit3);
        roomReservation3.setVisit(visit3);

        log.debug("Loaded data: Visits");
    }
}
