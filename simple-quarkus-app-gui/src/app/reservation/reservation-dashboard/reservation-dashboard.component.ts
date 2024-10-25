import { Component, inject, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';
import { Reservation } from '../../../client/reservation/model/reservation';
import { ReservationService } from '../reservation.service';
import { ReservationComponent } from '../reservation/reservation.component';

@Component({
  selector: 'app-reservation-dashboard',
  standalone: true,
  imports: [
    ReservationComponent
  ],
  templateUrl: './reservation-dashboard.component.html',
  styleUrl: './reservation-dashboard.component.css'
})
export class ReservationDashboardComponent implements OnInit {
  private router = inject(Router);

  reservations = signal<Reservation[]>([]);

  private reservationService = inject(ReservationService);

  ngOnInit(): void {
    this.reservationService.getReservations()
      .subscribe(
        (response) => this.reservations.set(response)
      )
  }

  onAddReservation() {
    this.router.navigate(['/new-reservation']);
  }
}
