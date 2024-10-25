import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Reservation } from '../../client/reservation/model/reservation';
import { ReservationContent } from '../../client/reservation/model/reservationContent';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private httpClient = inject(HttpClient);

  getReservations() {
    return this.httpClient.get<Reservation[]>('/api/reservations');
  }

  addReservation(reservationContent: ReservationContent) {
    return this.httpClient.post<Reservation>('/api/reservations', reservationContent);
  }
}
