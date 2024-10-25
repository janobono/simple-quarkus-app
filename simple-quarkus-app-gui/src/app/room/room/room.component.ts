import { Component, input } from '@angular/core';
import { Room } from '../../../client/room/model/room';
import { CardComponent } from '../../card/card.component';

@Component({
  selector: 'app-room',
  standalone: true,
  imports: [
    CardComponent
  ],
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent {
  room = input.required<Room>();
}
