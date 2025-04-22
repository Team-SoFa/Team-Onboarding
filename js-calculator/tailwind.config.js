/** @type {import('tailwindcss').Config} */

function withOpacity(variableName) {
    return ({ opacityValue }) => {
      if (opacityValue !== undefined) {
        return `rgba(var(${variableName}), ${opacityValue})`
      }
      return `rgb(var(${variableName}))`
    }
  }
  
  module.exports = {
    theme: {
      screens: {
        sm: '640px',
        md: '768px',
        lg: '1024px',
        xl: '1280px',
      },
  
      // Uncomment the following extend
      // if existing Tailwind color palette will be used
  
      extend: {
        textColor: {
          skin: {
            base: withOpacity('--color-text-base'),
            accent: withOpacity('--color-accent'),
            inverted: withOpacity('--color-fill'),
          },
        },
        backgroundColor: {
          skin: {
            fill: withOpacity('--color-fill'),
            accent: withOpacity('--color-accent'),
            inverted: withOpacity('--color-text-base'),
            card: withOpacity('--color-card'),
            'card-muted': withOpacity('--color-card-muted'),
          },
        },
        outlineColor: {
          skin: {
            fill: withOpacity('--color-accent'),
          },
        },
        borderColor: {
          skin: {
            line: withOpacity('--color-border'),
            fill: withOpacity('--color-text-base'),
            accent: withOpacity('--color-accent'),
          },
        },
        fill: {
          skin: {
            base: withOpacity('--color-text-base'),
            accent: withOpacity('--color-accent'),
          },
          transparent: 'transparent',
        },
        fontFamily: {
          mono: ['Open Sans', 'sans-serif'],
        },
      },
    },
    plugins: [require('@tailwindcss/typography')],
  }