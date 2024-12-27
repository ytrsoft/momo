

const setup = () => {
  const modules = Process.enumerateModulesSync()
  modules.forEach((module) => {
    if(!module.name) {
      console.log(module)
    }
  })
}

Java.perform(setup)
