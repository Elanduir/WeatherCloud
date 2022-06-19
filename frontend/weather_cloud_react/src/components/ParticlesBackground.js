import Particles from "react-tsparticles"
import config from "./particlesConfig/config"


const ParticlesBackground = () => {
  return (
    <Particles params={config}></Particles>
  )
}

export default ParticlesBackground