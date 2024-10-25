import { Component, input } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navbar-item',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './navbar-item.component.html',
  styleUrl: './navbar-item.component.css'
})
export class NavbarItemComponent {
  title = input.required<string>();
  link = input.required<string>();
}
