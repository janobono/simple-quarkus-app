import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { RoomReservation } from '../../client/room-reservation/model/roomReservation';

@Injectable({
  providedIn: 'root'
})
export class RoomReservationService {

  private httpClient = inject(HttpClient);

  getRoomReservations(date: Date) {
    return this.httpClient.get<RoomReservation[]>('/api/room-reservations/' + this.formatDate(date));
  }

  private formatDate(value: Date) {
    const year = `${value.getFullYear()}`.padStart(4, '0');
    const month = `${value.getMonth() + 1}`.padStart(2, '0');
    const day = `${value.getDate()}`.padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
}
