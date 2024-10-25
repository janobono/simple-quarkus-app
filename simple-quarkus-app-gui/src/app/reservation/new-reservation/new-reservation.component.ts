import { Component, inject, signal } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ReservationService } from '../reservation.service';

@Component({
  selector: 'app-new-reservation',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './new-reservation.component.html',
  styleUrl: './new-reservation.component.css'
})
export class NewReservationComponent {
  private router = inject(Router);
  private reservationService = inject(ReservationService);

  roomId = signal('');
  customerId = signal('');
  date = signal('');

  onCancel() {
    this.router.navigate(['/reservations']);
  }

  onSubmit() {
    this.reservationService.addReservation({
      roomId: Number(this.roomId()),
      customerId: Number(this.customerId()),
      date: new Date(Date.parse(this.date())).toISOString()
    })
      .subscribe(
        (response) => {
          console.log(response);
          this.router.navigate(['/reservations']);
        }
      )
  }
}
