import { DatePipe } from '@angular/common';
import { Component, input } from '@angular/core';
import { Reservation } from '../../../client/reservation/model/reservation';
import { CardComponent } from '../../card/card.component';

@Component({
  selector: 'app-reservation',
  standalone: true,
  imports: [
    DatePipe,
    CardComponent
  ],
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent {
  reservation = input.required<Reservation>();
}
