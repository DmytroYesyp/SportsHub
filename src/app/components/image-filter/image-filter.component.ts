import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-image-filter',
  templateUrl: './image-filter.component.html',
  styleUrls: ['./image-filter.component.css']
})
export class ImageFilterComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    const grayscale = document.querySelector('#grayscale')
    const contrast = document.querySelector('#contrast')
    const brightness = document.querySelector('#brightness')
    const sepia = document.querySelector('#sepia')
    const saturate = document.querySelector('#saturate')
    const img = document.querySelector('#image')
    const reset = document.querySelector('#reset')
    const imgUrl = document.querySelector('#img-url')

    const defaults = {
      grayscale: 0,
      contrast: 100,
      brightness: 100,
      sepia: 0,
      saturate: 100
    }

    // @ts-ignore
    grayscale.addEventListener('input', updateFilterValue)
    // @ts-ignore
    contrast.addEventListener('input', updateFilterValue)
    // @ts-ignore
    brightness.addEventListener('input', updateFilterValue)
    // @ts-ignore
    sepia.addEventListener('input', updateFilterValue)
    // @ts-ignore
    saturate.addEventListener('input', updateFilterValue)
    // @ts-ignore
    reset.addEventListener('click', resetFilterValue)
    // @ts-ignore
    imgUrl.addEventListener('change', updateImageUrl)

    function updateFilterValue() {
      // @ts-ignore
      reset.disabled = false
      // @ts-ignore

      img.style.filter = `\n    grayscale(${grayscale.value}%)\n    contrast(${contrast.value}%)\n    brightness(${brightness.value}%)\n    sepia(${sepia.value}%)\n    saturate(${saturate.value}%)\n  `
    }

    function resetFilterValue() {
      console.log('click')
      // @ts-ignore
      img.style.filter = `
    grayscale(${defaults.grayscale}%)
    contrast(${defaults.contrast}%)
    brightness(${defaults.brightness}%)
    sepia(${defaults.sepia}%)
    saturate(${defaults.saturate}%)
  `
      // @ts-ignore
      grayscale.value = defaults.grayscale
      // @ts-ignore
      contrast.value = defaults.contrast
      // @ts-ignore
      brightness.value = defaults.brightness
      // @ts-ignore
      sepia.value = defaults.sepia
      // @ts-ignore
      saturate.value = defaults.saturate
      // @ts-ignore
      reset.disabled = true
    }

    function updateImageUrl() {
      // @ts-ignore
      const imageUrl = imgUrl.value
      // @ts-ignore
      img.setAttribute('src', imageUrl)
    }
  }

}
