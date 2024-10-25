import { Component, inject, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';
import { Room } from '../../../client/room/model/room';
import { RoomService } from '../room.service';
import { RoomComponent } from '../room/room.component';

@Component({
  selector: 'app-room-dashboard',
  standalone: true,
  imports: [
    RoomComponent
  ],
  templateUrl: './room-dashboard.component.html',
  styleUrl: './room-dashboard.component.css'
})
export class RoomDashboardComponent implements OnInit {
  private router = inject(Router);

  rooms = signal<Room[]>([]);

  private roomService = inject(RoomService);

  ngOnInit(): void {
    this.roomService.getRooms()
      .subscribe(
        (response) => this.rooms.set(response)
      )
  }

  onAddRoom() {
    this.router.navigate(['/new-room']);
  }
}
