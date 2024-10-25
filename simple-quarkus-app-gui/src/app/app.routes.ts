import { Routes } from '@angular/router';
import { CustomerDashboardComponent } from './customer/customer-dashboard/customer-dashboard.component';
import { NewCustomerComponent } from './customer/new-customer/new-customer.component';
import { NewReservationComponent } from './reservation/new-reservation/new-reservation.component';
import { ReservationDashboardComponent } from './reservation/reservation-dashboard/reservation-dashboard.component';
import {
  RoomReservationDashboardComponent
} from './room-reservation/room-reservation-dashboard/room-reservation-dashboard.component';
import { NewRoomComponent } from './room/new-room/new-room.component';
import { RoomDashboardComponent } from './room/room-dashboard/room-dashboard.component';

export const routes: Routes = [
  {
    path: 'customers',
    component: CustomerDashboardComponent
  },
  {
    path: 'new-customer',
    component: NewCustomerComponent
  },
  {
    path: 'reservations',
    component: ReservationDashboardComponent
  },
  {
    path: 'new-reservation',
    component: NewReservationComponent
  },
  {
    path: 'rooms',
    component: RoomDashboardComponent
  },
  {
    path: 'new-room',
    component: NewRoomComponent
  },
  {
    path: 'room-reservations',
    component: RoomReservationDashboardComponent
  },
  {
    path: '',
    redirectTo: '/room-reservations',
    pathMatch: 'full'
  }
];
