import { DatePipe } from '@angular/common';
import { Component, input } from '@angular/core';
import { RoomReservation } from '../../../client/room-reservation/model/roomReservation';
import { CardComponent } from '../../card/card.component';

@Component({
  selector: 'app-room-reservation',
  standalone: true,
  imports: [
    CardComponent,
    DatePipe
  ],
  templateUrl: './room-reservation.component.html',
  styleUrl: './room-reservation.component.css'
})
export class RoomReservationComponent {
  roomReservation = input.required<RoomReservation>();
}
