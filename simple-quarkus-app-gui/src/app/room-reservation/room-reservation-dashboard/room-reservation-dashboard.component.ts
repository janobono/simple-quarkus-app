import { Component, inject, signal } from '@angular/core';
import { toObservable } from '@angular/core/rxjs-interop';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RoomReservation } from '../../../client/room-reservation/model/roomReservation';
import { RoomReservationService } from '../room-reservation.service';
import { RoomReservationComponent } from '../room-reservation/room-reservation.component';

@Component({
  selector: 'app-room-reservation-dashboard',
  standalone: true,
  imports: [
    RoomReservationComponent,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './room-reservation-dashboard.component.html',
  styleUrl: './room-reservation-dashboard.component.css'
})
export class RoomReservationDashboardComponent {
  date = signal('');

  roomReservations = signal<RoomReservation[]>([]);

  private roomReservationService = inject(RoomReservationService);

  constructor() {
    toObservable(this.date).subscribe(() => this.reloadRoomReservations());
  }

  private reloadRoomReservations(): void {
    if (this.date().length > 0) {
      console.log(this.date());
      this.roomReservationService.getRoomReservations(new Date(Date.parse(this.date())))
        .subscribe(
          (response) => {
            console.log(response);
            this.roomReservations.set(response)
          }
        );
    }
  }
}
